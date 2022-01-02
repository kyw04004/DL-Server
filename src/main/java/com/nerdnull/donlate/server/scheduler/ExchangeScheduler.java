package com.nerdnull.donlate.server.scheduler;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.nerdnull.donlate.server.dto.ExchangeDto;
import com.nerdnull.donlate.server.mapper.ExchangeMapper;
import com.nerdnull.donlate.server.repository.ExchangeRepository;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ExchangeScheduler {

    private final ExchangeRepository exchangeRepository;
    private final ExchangeMapper exchangeMapper = Mappers.getMapper(ExchangeMapper.class);
    private final AmazonS3 S3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();
    private final String BUCKET_NAME = "donlate-bucket";

    public ExchangeScheduler(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    @Scheduled(cron="0 0 17 * * *") // 매일 17시
    public void run() throws Exception {
        try {
            log.info("Exchange scheduling job start--{}", LocalTime.now());

            LocalDateTime now = LocalDateTime.now();
            List<ExchangeDto> exchangeList = this.exchangeMapper.toDtoList(
                    this.exchangeRepository.findAllByRequestedAtBefore(Date.from(now.atZone(ZoneId.systemDefault()).toInstant())));

            if(exchangeList.isEmpty()) {
                log.info("Exchange list is empty.");
                log.info("Exchange scheduling job end--{}", LocalTime.now());
                return;
            }

            this.exchangeRepository.deleteAllByRequestedAtBefore(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));

            String key = String.format("exchangeList/%d/%d/%d-%d-%d.csv",now.getYear(), now.getMonthValue(), now.getYear(), now.getMonthValue(), now.getDayOfMonth());
            String path = String.format("%d-%d-%d.csv",now.getYear(), now.getMonthValue(), now.getDayOfMonth());

            try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
                for(ExchangeDto exchange : exchangeList){
                    String[] column = {exchange.getBank(), exchange.getAccount(), String.valueOf(exchange.getAmount()),
                    exchange.getName(), "NerdNULL (DON-LATE)"};
                    writer.writeNext(column);
                }
            } catch (IOException e) {
                log.error("Error While writing CSV ", e);
            }

            File file = new File(path);
            if(!file.exists()) throw new Exception("CSV file does not exist.");

            // aws s3 save logic
            try {
                if (!this.S3Client.doesBucketExistV2(BUCKET_NAME))
                    throw new Exception("The bucket does not exist.");
                if (this.S3Client.doesObjectExist(BUCKET_NAME, key))
                    throw new Exception("The object already exists.");

                this.S3Client.putObject(BUCKET_NAME, key, file);

            } catch (Exception e){
                throw new Exception("Failed to exchange.\n" + e.getMessage());
            }
            //

            if(!file.delete()) throw new Exception("CSV file does not exist.");

            log.info("Exchange scheduling job end--{}", LocalTime.now());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
