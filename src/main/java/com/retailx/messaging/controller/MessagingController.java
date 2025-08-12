package com.retailx.messaging.controller;

import com.retailx.messaging.model.MessageRequest;
import com.retailx.messaging.model.MessageResponse;
import com.retailx.messaging.service.MessagingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Modern Messaging Controller
 * 
 * REST API endpoints for the RetailX Messaging Hub.
 * 
 * MODERN FEATURES:
 * - Multi-channel message delivery
 * - Advanced filtering and pagination
 * - Rich content and template support
 * - Comprehensive delivery analytics
 * - Proper error handling and status codes
 * 
 * MIGRATION IMPROVEMENTS over Legacy:
 * - RESTful API design with proper HTTP status codes
 * - Comprehensive OpenAPI documentation
 * - Enhanced request/response models
 * - Better error handling and validation
 * - Support for bulk operations and filtering
 */
@RestController
@RequestMapping("/v2")
@Tag(name = "Modern Messaging Hub", description = "Next-generation messaging service replacing legacy notifications")
public class MessagingController {
    
    private final MessagingService messagingService;
    
    @Autowired
    public MessagingController(MessagingService messagingService) {
        this.messagingService = messagingService;
    }
    
    /**
     * Send a message through multiple channels
     * 
     * MODERN ENDPOINT: /v2/messages
     * 
     * ENHANCED FEATURES vs Legacy:
     * - Multi-channel delivery (email, SMS, push, in-app, webhook)
     * - Template support with variable substitution
     * - Scheduled delivery
     * - Priority levels and delivery preferences
     * - Rich content support (HTML, attachments)
     * - Comprehensive delivery tracking
     */
    @Operation(
        summary = "Send a multi-channel message",
        description = "Send a message through multiple delivery channels with enhanced tracking, " +
                     "template support, and delivery analytics. Supports scheduling, priority levels, " +
                     "and webhook callbacks for delivery status."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Message queued successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "429", description = "Rate limit exceeded"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/messages")
    public ResponseEntity<MessageResponse> sendMessage(@Valid @RequestBody MessageRequest request) {
        try {
            MessageResponse response = messagingService.sendMessage(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Get message status by ID with detailed tracking
     * 
     * MODERN ENDPOINT: /v2/messages/{messageId}
     * 
     * ENHANCED FEATURES vs Legacy:
     * - Detailed delivery status per channel
     * - Delivery analytics and metrics
     * - Retry attempt history
     * - Webhook callback status
     */
    @Operation(
        summary = "Get message status and analytics",
        description = "Retrieve detailed status information for a message including per-channel " +
                     "delivery status, analytics, retry attempts, and delivery timestamps."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Message found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
        @ApiResponse(responseCode = "404", description = "Message not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<MessageResponse> getMessageStatus(
        @Parameter(description = "Unique message identifier", required = true, example = "msg_550e8400-e29b-41d4-a716-446655440000")
        @PathVariable String messageId) {
        
        MessageResponse response = messagingService.getMessageById(messageId);
        
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /**
     * List messages with filtering and pagination
     * 
     * MODERN ENDPOINT: /v2/messages
     * 
     * NEW FEATURE: Advanced filtering and pagination not available in legacy API
     * - Filter by status, category, priority
     * - Pagination support
     * - Sorting options
     */
    @Operation(
        summary = "List messages with filtering",
        description = "Retrieve a paginated list of messages with optional filtering by status, " +
                     "category, and other criteria. Includes delivery analytics for each message."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Messages retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid query parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/messages")
    public ResponseEntity<List<MessageResponse>> getMessages(
        @Parameter(description = "Filter by message status", example = "delivered")
        @RequestParam(required = false) String status,
        
        @Parameter(description = "Filter by message category", example = "order_updates")
        @RequestParam(required = false) String category,
        
        @Parameter(description = "Page number (0-based)", example = "0")
        @RequestParam(defaultValue = "0") int page,
        
        @Parameter(description = "Page size", example = "20")
        @RequestParam(defaultValue = "20") int size) {
        
        try {
            List<MessageResponse> messages = messagingService.getAllMessages(status, category, page, size);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
