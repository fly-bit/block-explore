package com.flybit.blockchain.services;

import com.flybit.blockchain.vo.BlocksVO;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowBlocksServices {

    public List<BlocksVO> fetchBlocks(final String name) {
        List<BlocksVO> blocksVOList = new ArrayList<>();
        blocksVOList = getMockOneData();
        return blocksVOList;
    }


    public List<BlocksVO> fetchAllBlocks() {
        List<BlocksVO> blocksVOList = new ArrayList<>();
        blocksVOList = getMockAllData();
        return blocksVOList;
    }

    private List<BlocksVO> getMockOneData() {
        List<BlocksVO> blocksVOList = new ArrayList<>();
        BlocksVO blocksVO1 = new BlocksVO();
        blocksVO1.setHeight("111111");
        blocksVO1.setHarvester("nbfpzg-11111-5ijizy-4knhaz-dntttq-yso7ih-idfb");
        blocksVOList.add(blocksVO1);
        return blocksVOList;
    }

    private List<BlocksVO> getMockAllData() {
        List<BlocksVO> blocksVOList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            BlocksVO blocksVO1 = new BlocksVO();
            String str = "" + i + i + i + i + i;
            blocksVO1.setHeight(str);
            blocksVO1.setHarvester("nbfpzg-" + str + "-5ijizy-4knhaz-dntttq-yso7ih-idfb");
            blocksVOList.add(blocksVO1);
        }
        return blocksVOList;
    }
}
