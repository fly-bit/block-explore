package com.flybit.blockchain.services;

import com.flybit.blockchain.vo.BlocksVO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<BlocksVO> findAllPageable(Pageable pageable) {
        List<BlocksVO> blocksVOList = new ArrayList<>();
        blocksVOList = getMockAllData(pageable);
        Page<BlocksVO> result = new PageImpl<>(blocksVOList, pageable, pageable.getPageSize() * 4 + 1);
        return result;
    }

    private List<BlocksVO> getMockAllData(Pageable pageable) {
        List<BlocksVO> blocksVOList = new ArrayList<>();
        if (pageable.getPageNumber() == 4) {
            BlocksVO blocksVO1 = new BlocksVO();
            blocksVO1.setHeight("5x5x5x");
            blocksVO1.setHarvester("nbfpzg-5x5x5x-5ijizy-4knhaz-dntttq-yso7ih-idfb");
            blocksVOList.add(blocksVO1);
            return blocksVOList;
        }
        for (int i = 1; i <= pageable.getPageSize(); i++) {
            int height = i * (pageable.getPageNumber() + 1);
            BlocksVO blocksVO1 = new BlocksVO();
            String str = "" + height + height + height + height;
            blocksVO1.setHeight(str);
            blocksVO1.setHarvester("nbfpzg-" + str + "-5ijizy-4knhaz-dntttq-yso7ih-idfb");
            blocksVOList.add(blocksVO1);
        }
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
