package com.nerdnull.donlate.server.scheduler;

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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ExchangeScheduler {

    private final ExchangeRepository exchangeRepository;
    private final ExchangeMapper exchangeMapper = Mappers.getMapper(ExchangeMapper.class);

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
            this.exchangeRepository.deleteAllByRequestedAtBefore(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));

            String path = String.format("./%s-%d-%d.csv",now.getYear(),now.getMonthValue(),now.getDayOfMonth());
            try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
                for(ExchangeDto exchange : exchangeList){
                    String[] column = {exchange.getBank(), exchange.getAccount(), String.valueOf(exchange.getAmount()),
                    exchange.getName() + " 환전 신청", "NerdNULL (DON-LATE)"};
                    writer.writeNext(column);
                }
            } catch (IOException e) {
                log.error("Error While writing CSV ", e);
            }

            // aws s3 save logic

            //

            File file = new File(path);
            if(!file.delete()) throw new Exception("CSV file does not exist.");

            log.info("Exchange scheduling job end--{}", LocalTime.now());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
