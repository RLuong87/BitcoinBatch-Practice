package com.spring.batch.config;

import com.spring.batch.entity.BitcoinData;
import org.springframework.batch.item.ItemProcessor;

public class BitcoinProcessor implements ItemProcessor<BitcoinData, BitcoinData> {

    @Override
    public BitcoinData process(BitcoinData bitcoinData) throws Exception {
        return bitcoinData;
    }
}
