package com.flybit.blockchain.controller;

import com.flybit.blockchain.services.ShowBlocksServices;
import com.flybit.blockchain.vo.BlocksVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShowBlocksController {

    @Autowired
    private ShowBlocksServices showBlocksServices;

    @RequestMapping("blocks")
    public String blocks(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        List<BlocksVO> blocksVOList = null;
        if (name != null && name.trim().length() > 0) {
            blocksVOList = showBlocksServices.fetchBlocks(name);
        } else {
            blocksVOList = showBlocksServices.fetchAllBlocks();
        }
        model.addAttribute("blocksVOList", blocksVOList);
        return "blocks";
    }

    @RequestMapping("searchBlocks")
    public String searchBlocks(String harvester, Model model) {
        model.addAttribute("harvester", harvester);
        List<BlocksVO> blocksVOList = showBlocksServices.fetchBlocks(harvester);
        model.addAttribute("blocksVOList", blocksVOList);
        return "blocks";
    }
}
