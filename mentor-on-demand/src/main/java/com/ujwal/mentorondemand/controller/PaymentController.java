package com.ujwal.mentorondemand.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ujwal.mentorondemand.exception.ResourceNotFoundException;
import com.ujwal.mentorondemand.model.Payment;
import com.ujwal.mentorondemand.model.Wallet;
import com.ujwal.mentorondemand.repository.PaymentRepository;
import com.ujwal.mentorondemand.repository.UserRepository;
import com.ujwal.mentorondemand.repository.WalletRepository;

@RestController
@RequestMapping("/api")
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/payments/{page}/{size}")
	public Page<Payment> findAllPayments(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return paymentRepository.findAll(PageRequest.of(page, size));
	}
	
	@GetMapping("/payments/user/{userId}/{page}/{size}")
	public Page<Payment> findPaymentsByUser(@PathVariable(value = "userId") long userId, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return paymentRepository.findByUserId(userId, PageRequest.of(page, size));
	}
	
	@GetMapping("/payments/course/{courseId}/{page}/{size}")
	public Page<Payment> findPaymentsByCourse(@PathVariable(value = "courseId") long courseId, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return paymentRepository.findByCourseId(courseId, PageRequest.of(page, size));
	}
	
	@PostMapping("/payment")
	public Payment createUser(@Valid @RequestBody Payment payment) {
		return paymentRepository.save(payment);
	}
	
	@GetMapping("/addToWallet/{userId}/{amount}")
	public Wallet addToWallet(@PathVariable(value = "userId") long userId, @PathVariable(value = "amount") long amount) {
		Wallet wallet = (userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId))).getWallet();
		double balance = wallet.getBalance() + amount ;
		wallet.setBalance(balance);
		return walletRepository.save(wallet);
	}
	
	@GetMapping("/deductFromWallet/{userId}/{amount}")
	public Wallet deductFromWallet(@PathVariable(value = "userId") long userId, @PathVariable(value = "amount") long amount) {
		Wallet wallet = (userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId))).getWallet();
		double balance = wallet.getBalance() - amount ;
		wallet.setBalance(balance);
		return walletRepository.save(wallet);
	}
}
