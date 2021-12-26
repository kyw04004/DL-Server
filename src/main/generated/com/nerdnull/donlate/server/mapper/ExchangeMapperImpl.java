package com.nerdnull.donlate.server.mapper;

import com.nerdnull.donlate.server.domain.ExchangeEntity;
import com.nerdnull.donlate.server.dto.ExchangeDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-26T12:55:53+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class ExchangeMapperImpl implements ExchangeMapper {

    @Override
    public List<ExchangeDto> toDtoList(List<ExchangeEntity> e) {
        if ( e == null ) {
            return null;
        }

        List<ExchangeDto> list = new ArrayList<ExchangeDto>( e.size() );
        for ( ExchangeEntity exchangeEntity : e ) {
            list.add( toDto( exchangeEntity ) );
        }

        return list;
    }

    @Override
    public List<ExchangeEntity> toEntityList(List<ExchangeDto> d) {
        if ( d == null ) {
            return null;
        }

        List<ExchangeEntity> list = new ArrayList<ExchangeEntity>( d.size() );
        for ( ExchangeDto exchangeDto : d ) {
            list.add( toEntity( exchangeDto ) );
        }

        return list;
    }

    @Override
    public ExchangeDto toDto(ExchangeEntity exchangeEntity) {
        if ( exchangeEntity == null ) {
            return null;
        }

        Long exchangeId = null;
        String bank = null;
        String account = null;
        Long amount = null;
        String name = null;
        Date requestedAt = null;

        exchangeId = exchangeEntity.getExchangeId();
        bank = exchangeEntity.getBank();
        account = exchangeEntity.getAccount();
        amount = exchangeEntity.getAmount();
        name = exchangeEntity.getName();
        requestedAt = exchangeEntity.getRequestedAt();

        ExchangeDto exchangeDto = new ExchangeDto( exchangeId, bank, account, amount, name, requestedAt );

        return exchangeDto;
    }

    @Override
    public ExchangeEntity toEntity(ExchangeDto exchangeDto) {
        if ( exchangeDto == null ) {
            return null;
        }

        ExchangeEntity exchangeEntity = new ExchangeEntity();

        exchangeEntity.setExchangeId( exchangeDto.getExchangeId() );
        exchangeEntity.setBank( exchangeDto.getBank() );
        exchangeEntity.setAccount( exchangeDto.getAccount() );
        exchangeEntity.setAmount( exchangeDto.getAmount() );
        exchangeEntity.setName( exchangeDto.getName() );
        exchangeEntity.setRequestedAt( exchangeDto.getRequestedAt() );

        return exchangeEntity;
    }
}
