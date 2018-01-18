package com.flybit.blockchain.controller;

import com.flybit.blockchain.domain.Pager;
import com.flybit.blockchain.services.ShowBlocksServices;
import com.flybit.blockchain.vo.BlocksVO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ShowBlocksController {

    private static Logger logger = Logger.getLogger(ShowBlocksController.class);

    @Autowired
    private ShowBlocksServices showBlocksServices;

    @RequestMapping("/")
    public String defBlocks(@RequestParam(value = "name", required = false) String name, Model model) {
        logger.info("name:" + name);
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

    @RequestMapping("blocks")
    public String blocks(@RequestParam(value = "name", required = false) String name, Model model) {
        logger.info("name:" + name);
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
        logger.info("harvester:" + harvester);
        model.addAttribute("harvester", harvester);
        List<BlocksVO> blocksVOList = showBlocksServices.fetchBlocks(harvester);
        model.addAttribute("blocksVOList", blocksVOList);
        return "blocks";
    }

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @RequestMapping("/paging")
    public ModelAndView blocksInPaging(@RequestParam("pageSize") Optional<Integer> pageSize,
                                       @RequestParam("page") Optional<Integer> page) {
        ModelAndView modelAndView = new ModelAndView("pagingBlocks");

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<BlocksVO> blocksVOS = showBlocksServices.findAllPageable(new PageRequest(evalPage, evalPageSize));
        Pager pager = new Pager(blocksVOS.getTotalPages(), blocksVOS.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("blocksVOS", blocksVOS);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        return modelAndView;
    }

}
