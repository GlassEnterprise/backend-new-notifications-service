package com.retailx.messaging.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Modern Message Response Model
 * 
 * Enhanced response model providing detailed delivery information,
 * analytics, and tracking capabilities.
 * 
 * MODERN FEATURES vs Legacy:
 * - Detailed delivery status per channel
 * - Delivery analytics and metrics
 * - Retry information and attempt history
 * - Rich metadata and tracking
 * - Webhook callback status
 */
@Schema(description = "Response containing message delivery information and status")
public class MessageResponse {
    
    @Schema(description = "Unique message identifier", example = "msg_550e8400-e29b-41d4-a716-446655440000")
    private String messageId;
    
    @Schema(description = "List of recipients", example = "[\"user@example.com\", \"admin@retailx.com\"]")
    private List<String> recipients;
    
    @Schema(description = "Message content", example = "Your order #12345 has been shipped!")
    private String content;
    
    @Schema(description = "Delivery channels used", example = "[\"email\", \"push\"]")
    private List<String> channels;
    
    @Schema(description = "Overall message status", example = "delivered", 
            allowableValues = {"queued", "processing", "delivered", "partially_delivered", "failed"})
    private String status;
    
    @Schema(description = "Priority level", example = "high")
    private String priority;
    
    @Schema(description = "Message category", example = "order_updates")
    private String category;
    
    @Schema(description = "Message creation timestamp", example = "2024-01-15T10:30:00")
    private LocalDateTime createdAt;
    
    @Schema(description = "Last update timestamp", example = "2024-01-15T10:31:15")
    private LocalDateTime updatedAt;
    
    @Schema(description = "Scheduled delivery time", example = "2024-01-15T14:30:00")
    private LocalDateTime scheduledAt;
    
    @Schema(description = "Actual delivery time", example = "2024-01-15T14:30:05")
    private LocalDateTime deliveredAt;
    
    @Schema(description = "Delivery status per channel")
    private Map<String, ChannelDeliveryStatus> channelStatus;
    
    @Schema(description = "Delivery analytics and metrics")
    private DeliveryAnalytics analytics;
    
    // MOCK DATA INDICATOR: This field indicates demo/mock data
    @Schema(description = "Indicates if this is mock data for demo purposes", example = "true")
    private boolean mockData = true;
    
    // Nested classes for structured data
    @Schema(description = "Delivery status for a specific channel")
    public static class ChannelDeliveryStatus {
        @Schema(description = "Channel name", example = "email")
        private String channel;
        
        @Schema(description = "Delivery status", example = "delivered")
        private String status;
        
        @Schema(description = "Delivery timestamp", example = "2024-01-15T14:30:05")
        private LocalDateTime deliveredAt;
        
        @Schema(description = "Number of delivery attempts", example = "1")
        private int attempts;
        
        @Schema(description = "Error message if delivery failed")
        private String errorMessage;
        
        // Constructors, getters, and setters
        public ChannelDeliveryStatus() {}
        
        public ChannelDeliveryStatus(String channel, String status) {
            this.channel = channel;
            this.status = status;
            this.attempts = 1;
        }
        
        // Getters and setters
        public String getChannel() { return channel; }
        public void setChannel(String channel) { this.channel = channel; }
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        public LocalDateTime getDeliveredAt() { return deliveredAt; }
        public void setDeliveredAt(LocalDateTime deliveredAt) { this.deliveredAt = deliveredAt; }
        
        public int getAttempts() { return attempts; }
        public void setAttempts(int attempts) { this.attempts = attempts; }
        
        public String getErrorMessage() { return errorMessage; }
        public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    }
    
    @Schema(description = "Analytics and metrics for message delivery")
    public static class DeliveryAnalytics {
        @Schema(description = "Total recipients", example = "2")
        private int totalRecipients;
        
        @Schema(description = "Successfully delivered", example = "2")
        private int delivered;
        
        @Schema(description = "Failed deliveries", example = "0")
        private int failed;
        
        @Schema(description = "Pending deliveries", example = "0")
        private int pending;
        
        @Schema(description = "Delivery rate percentage", example = "100.0")
        private double deliveryRate;
        
        // Constructors, getters, and setters
        public DeliveryAnalytics() {}
        
        public DeliveryAnalytics(int totalRecipients, int delivered, int failed, int pending) {
            this.totalRecipients = totalRecipients;
            this.delivered = delivered;
            this.failed = failed;
            this.pending = pending;
            this.deliveryRate = totalRecipients > 0 ? (double) delivered / totalRecipients * 100 : 0;
        }
        
        // Getters and setters
        public int getTotalRecipients() { return totalRecipients; }
        public void setTotalRecipients(int totalRecipients) { this.totalRecipients = totalRecipients; }
        
        public int getDelivered() { return delivered; }
        public void setDelivered(int delivered) { this.delivered = delivered; }
        
        public int getFailed() { return failed; }
        public void setFailed(int failed) { this.failed = failed; }
        
        public int getPending() { return pending; }
        public void setPending(int pending) { this.pending = pending; }
        
        public double getDeliveryRate() { return deliveryRate; }
        public void setDeliveryRate(double deliveryRate) { this.deliveryRate = deliveryRate; }
    }
    
    // Constructors
    public MessageResponse() {}
    
    public MessageResponse(String messageId, List<String> recipients, String content, List<String> channels) {
        this.messageId = messageId;
        this.recipients = recipients;
        this.content = content;
        this.channels = channels;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }
    
    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public List<String> getChannels() { return channels; }
    public void setChannels(List<String> channels) { this.channels = channels; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }
    
    public LocalDateTime getDeliveredAt() { return deliveredAt; }
    public void setDeliveredAt(LocalDateTime deliveredAt) { this.deliveredAt = deliveredAt; }
    
    public Map<String, ChannelDeliveryStatus> getChannelStatus() { return channelStatus; }
    public void setChannelStatus(Map<String, ChannelDeliveryStatus> channelStatus) { this.channelStatus = channelStatus; }
    
    public DeliveryAnalytics getAnalytics() { return analytics; }
    public void setAnalytics(DeliveryAnalytics analytics) { this.analytics = analytics; }
    
    public boolean isMockData() { return mockData; }
    public void setMockData(boolean mockData) { this.mockData = mockData; }
}
