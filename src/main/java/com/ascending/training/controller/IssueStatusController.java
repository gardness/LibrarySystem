//package com.ascending.training.controller;
//
//import com.ascending.training.model.IssueStatus;
//import com.ascending.training.service.IssueStatusService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@RestController
//@RequestMapping(value = {"/issuestatuses"})
//public class IssueStatusController {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private IssueStatusService issueStatusService;
//
//    @GetMapping(value = "", produces = "application/json")
//    public List<IssueStatus> getIssueStatuses() {
//        return issueStatusService.getIssueStatuses();
//    }
//
//    @GetMapping(value = "/{IssueStatusId}", produces = "application/json")
//    public IssueStatus getIssueStatus(@PathVariable(name = "IssueStatusId") long issueStatusId) {
//        IssueStatus issueStatus = issueStatusService.getIssueStatusById(issueStatusId);
//
//        if (issueStatus != null) {
//            return issueStatus;
//        }
//
//        return null;
//    }
//
//    @PostMapping(value = "", produces = "application/json")
//    public String createIssueStatus(@RequestBody IssueStatus issueStatus) {
//        logger.debug(String.format("Issue Status : %s", issueStatus.toString()));
//
//        String msg = "The issue status has been created.";
//        long isSuccess = issueStatusService.save(issueStatus);
//
//        if (isSuccess == 0) {
//            msg = "The issue status has not been created.";
//        }
//
//        return msg;
//    }
//
//    @PutMapping(value = "/{id}", produces = "application/json")
//    public String updateIssueStatus(@PathVariable(name = "id") long issueStatusId, @RequestBody IssueStatus issueStatus) {
//        logger.debug(String.format("Issue Status ID : %d, Issue Status : %s", issueStatusId, issueStatus.toString()));
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
//
//        try {
//            Date issueDate = simpleDateFormat.parse(issueStatus.getIssueDate());
//            this.returnDate = simpleDateFormat.parse(returnDate);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        IssueStatus newIssueStatus = new IssueStatus(issueStatusId, issueStatus.getIssueDate(), issueStatus.getReturnDate());
//    }
//
//}
