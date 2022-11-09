package com.fsse2207.project_backend.api;


import com.fsse2207.project_backend.config.CrossOriginConfig;
import com.fsse2207.project_backend.data.transaction.TransactionDetailData;
import com.fsse2207.project_backend.data.transaction.dto.TransactionResponseDto;
import com.fsse2207.project_backend.data.transaction.dto.TransactionStatusResponseDto;
import com.fsse2207.project_backend.data.user.FirebaseUserData;
import com.fsse2207.project_backend.exception.*;
import com.fsse2207.project_backend.service.TransactionService;
import com.fsse2207.project_backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {CrossOriginConfig.devBaseUrl, CrossOriginConfig.productionBaseUrl, CrossOriginConfig.productionS3BaseUrl })
@RestController
@RequestMapping("/transaction")
public class TransactionApi {
    private TransactionService transactionService;

    @Autowired
    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public TransactionResponseDto createTransaction(JwtAuthenticationToken jwt) throws CartItemNotFoundException, TransactionExistedException {
        FirebaseUserData loggedUser = JwtUtil.getFirebaseUser(jwt);
        TransactionDetailData transactionDetailData = transactionService.createTransaction(loggedUser);
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto(transactionDetailData);
        return transactionResponseDto;
    }

    @GetMapping("/{tid}")
    public TransactionResponseDto getTransactionByTid(@PathVariable Integer tid) throws TransactionNotFoundException {
        TransactionDetailData transactionDetailData = transactionService.getTransactionByTid(tid);
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto(transactionDetailData);
        return transactionResponseDto;
    }

    @PatchMapping("/{tid}/pay")
    public TransactionStatusResponseDto payTransaction(@PathVariable Integer tid) throws OutOfStockException, TransactionNotFoundException, ProductNotFoundException, TransactionStatusException {
        TransactionDetailData transactionDetailData = transactionService.payTransaction(tid);
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto(transactionDetailData);
        return new TransactionStatusResponseDto();

    }

    @PatchMapping("/{tid}/finish")
    public TransactionResponseDto finishTransaction(@PathVariable Integer tid) throws CartItemNotFoundException, TransactionNotFoundException, TransactionStatusException {
        TransactionDetailData transactionDetailData = transactionService.successTransaction(tid);
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto(transactionDetailData);
        return transactionResponseDto;
    }

}
