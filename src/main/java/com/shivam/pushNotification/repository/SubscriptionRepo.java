package com.shivam.pushNotification.repository;
import com.shivam.pushNotification.entity.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface SubscriptionRepo extends MongoRepository<Subscription, Long> {
    
} 
