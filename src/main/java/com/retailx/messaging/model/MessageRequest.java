package com.retailx.messaging.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import java.util.Map;
import java.util.List;

/**
 * Modern Message Request Model
 * 
 * Enhanced request model for the new messaging hub, supporting rich content,
 * templates, scheduling, and multi-channel delivery.
 * 
 * MODERN FEATURES vs Legacy:
 * - Rich content support (HTML, templates, attachments)
 * - Template-based messaging with variables
 * - Scheduled delivery
 * - Multiple recipients
 * - Channel-specific configurations
 * - Priority levels and delivery preferences
 */
@Schema(description = "Request to send a message through the modern messaging hub")
public class MessageRequest {
    
    @Schema(description = "List of recipients for the message", example = "[\"user@example.com\", \"admin@retailx.com\"]")
    @NotNull(message = "Recipients list cannot be null")
    private List<String> recipients;
    
    @Schema(description = "Message content or template ID", example = "Your order #12345 has been shipped!")
    @NotBlank(message = "Content cannot be blank")
    private String content;
    
    @Schema(description = "Delivery channels", example = "[\"email\", \"push\"]", 
            allowableValues = {"email", "sms", "push", "in_app", "webhook"})
    @NotNull(message = "Channels cannot be null")
    private List<String> channels;
    
    @Schema(description = "Message priority level", example = "high", 
            allowableValues = {"low", "normal", "high", "urgent"})
    private String priority = "normal";
    
    @Schema(description = "Template ID for template-based messaging", example = "order_shipped_template")
    private String templateId;
    
    @Schema(description = "Variables for template substitution", 
            example = "{\"customerName\": \"John Doe\", \"orderNumber\": \"12345\"}")
    private Map<String, Object> templateVariables;
    
    @Schema(description = "Scheduled delivery time (ISO 8601)", example = "2024-01-15T14:30:00Z")
    private String scheduledAt;
    
    @Schema(description = "Message category for analytics", example = "order_updates")
    private String category;
    
    @Schema(description = "Webhook URL for delivery status callbacks", example = "https://api.retailx.com/webhooks/message-status")
    private String webhookUrl;
    
    // MOCK DATA INDICATOR: This field is used for demo purposes
    @Schema(description = "Demo mode flag - when true, uses mock delivery", example = "true")
    private boolean demoMode = true;
    
    // Constructors
    public MessageRequest() {}
    
    public MessageRequest(List<String> recipients, String content, List<String> channels) {
        this.recipients = recipients;
        this.content = content;
        this.channels = channels;
    }
    
    // Getters and Setters
    public List<String> getRecipients() { return recipients; }
    public void setRecipients(List<String> recipients) { this.recipients = recipients; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public List<String> getChannels() { return channels; }
    public void setChannels(List<String> channels) { this.channels = channels; }
    
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    
    public String getTemplateId() { return templateId; }
    public void setTemplateId(String templateId) { this.templateId = templateId; }
    
    public Map<String, Object> getTemplateVariables() { return templateVariables; }
    public void setTemplateVariables(Map<String, Object> templateVariables) { this.templateVariables = templateVariables; }
    
    public String getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(String scheduledAt) { this.scheduledAt = scheduledAt; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getWebhookUrl() { return webhookUrl; }
    public void setWebhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; }
    
    public boolean isDemoMode() { return demoMode; }
    public void setDemoMode(boolean demoMode) { this.demoMode = demoMode; }
}
