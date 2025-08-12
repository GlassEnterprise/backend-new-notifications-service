# RetailX Messaging Hub (New Notifications Service)

Modern, cloud-native notification service designed to replace the legacy `foo-legacy-notifications-api`. This service provides enhanced features including multi-channel delivery, advanced analytics, template support, and comprehensive tracking.

## 🚀 Modern Features

### Enhanced Capabilities vs Legacy
- **Multi-channel delivery**: Email, SMS, push notifications, in-app messages, webhooks
- **Rich content support**: HTML templates, attachments, personalization
- **Advanced analytics**: Delivery tracking, success rates, retry attempts
- **Template management**: Reusable templates with variable substitution
- **Scheduled delivery**: Send messages at specific times
- **Rate limiting**: Built-in throttling and quota management
- **Webhook callbacks**: Real-time delivery status updates
- **Horizontal scaling**: Cloud-native architecture for high availability

### Migration Benefits
- **Better error handling**: Comprehensive retry mechanisms and failure tracking
- **Modern observability**: Detailed metrics, logging, and monitoring
- **RESTful API design**: Proper HTTP status codes and resource-based endpoints
- **Enhanced security**: Input validation, rate limiting, and secure defaults
- **Developer experience**: Comprehensive OpenAPI documentation and examples

## 🏗️ Architecture

This service is part of the RetailX microservices ecosystem:

```
┌─────────────────┐    ┌──────────────────────┐    ┌─────────────────────┐
│   RetailX Web   │    │  RetailX Dashboard   │    │   External Apps     │
│   (Frontend)    │    │    (Frontend)        │    │                     │
└─────────┬───────┘    └──────────┬───────────┘    └─────────┬───────────┘
          │                       │                          │
          │ HTTP/REST             │ HTTP/REST                │ HTTP/REST
          │                       │                          │
          ▼                       ▼                          ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                    RetailX Messaging Hub (v2.0)                        │
│                   (backend-new-notifications-service)                  │
│                                                                         │
│  Modern Features:                                                       │
│  • Multi-channel delivery (email, SMS, push, in-app, webhook)         │
│  • Template management and personalization                             │
│  • Advanced delivery tracking and analytics                            │
│  • Rate limiting and throttling                                        │
│  • Scheduled message delivery                                          │
│  • Webhook callbacks for status updates                                │
│                                                                         │
│  Endpoints:                                                             │
│  • POST /v2/messages - Send multi-channel message                      │
│  • GET /v2/messages/{id} - Get message status and analytics            │
│  • GET /v2/messages - List messages with filtering                     │
│                                                                         │
│  Port: 8084                                                             │
└─────────────────────────────────────────────────────────────────────────┘
          │
          │ Replaces
          ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                 Legacy Notifications API (v1.0)                        │
│                  (foo-legacy-notifications-api)                        │
│                                                                         │
│  Legacy Features:                                                       │
│  • Basic email notifications only                                      │
│  • Simple request/response model                                       │
│  • Limited error handling                                              │
│  • No delivery tracking                                                │
│  • No analytics or metrics                                             │
│                                                                         │
│  Port: 8081                                                             │
└─────────────────────────────────────────────────────────────────────────┘
```

## 🛠️ Technology Stack

- **Java 17** - Modern LTS version with enhanced features
- **Spring Boot 3.2** - Latest framework with native compilation support
- **Spring WebFlux** - Reactive programming for better performance
- **Spring Data JPA** - Database abstraction layer
- **H2 Database** - In-memory database for demo purposes
- **SpringDoc OpenAPI** - Comprehensive API documentation
- **Maven** - Dependency management and build tool
- **Docker** - Containerization for consistent deployment

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use included wrapper)
- Docker (optional, for containerized deployment)

## 🚀 Quick Start

### Local Development

1. **Clone and navigate to the service**:
   ```bash
   cd backend-new-notifications-service
   ```

2. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the service**:
   - API Base URL: http://localhost:8084
   - Swagger UI: http://localhost:8084/swagger-ui.html
   - Health Check: http://localhost:8084/actuator/health
   - H2 Console: http://localhost:8084/h2-console

### Docker Deployment

1. **Build the Docker image**:
   ```bash
   docker build -t retailx-messaging-hub .
   ```

2. **Run the container**:
   ```bash
   docker run -p 8084:8084 retailx-messaging-hub
   ```

### Full Stack with Docker Compose

From the project root:
```bash
docker-compose up --build
```

## 📚 API Documentation

### Core Endpoints

#### Send Multi-Channel Message
```http
POST /v2/messages
Content-Type: application/json

{
  "recipients": ["user@example.com", "admin@retailx.com"],
  "content": "Your order #12345 has been shipped!",
  "channels": ["email", "push"],
  "priority": "high",
  "category": "order_updates",
  "templateId": "order_shipped_template",
  "templateVariables": {
    "customerName": "John Doe",
    "orderNumber": "12345"
  },
  "webhookUrl": "https://api.retailx.com/webhooks/message-status"
}
```

#### Get Message Status
```http
GET /v2/messages/{messageId}
```

#### List Messages with Filtering
```http
GET /v2/messages?status=delivered&category=order_updates&page=0&size=20
```

### Response Example
```json
{
  "messageId": "msg_550e8400-e29b-41d4-a716-446655440000",
  "recipients": ["user@example.com"],
  "content": "Your order #12345 has been shipped!",
  "channels": ["email", "push"],
  "status": "delivered",
  "priority": "high",
  "category": "order_updates",
  "createdAt": "2024-01-15T10:30:00",
  "deliveredAt": "2024-01-15T10:30:05",
  "channelStatus": {
    "email": {
      "channel": "email",
      "status": "delivered",
      "deliveredAt": "2024-01-15T10:30:05",
      "attempts": 1
    },
    "push": {
      "channel": "push",
      "status": "delivered",
      "deliveredAt": "2024-01-15T10:30:03",
      "attempts": 1
    }
  },
  "analytics": {
    "totalRecipients": 2,
    "delivered": 2,
    "failed": 0,
    "pending": 0,
    "deliveryRate": 100.0
  },
  "mockData": true
}
```

## 🔄 Migration from Legacy

### Key Differences from foo-legacy-notifications-api

| Feature | Legacy (v1.0) | Modern (v2.0) |
|---------|---------------|---------------|
| **Channels** | Email only | Email, SMS, Push, In-app, Webhook |
| **Recipients** | Single | Multiple |
| **Templates** | None | Full template engine with variables |
| **Analytics** | Basic success/failure | Detailed per-channel metrics |
| **Scheduling** | Immediate only | Scheduled delivery support |
| **Tracking** | Simple ID | Comprehensive delivery tracking |
| **Error Handling** | Basic | Advanced retry mechanisms |
| **API Design** | Simple POST | RESTful with proper status codes |
| **Documentation** | Minimal | Comprehensive OpenAPI spec |

### Migration Path

1. **Phase 1**: Deploy new service alongside legacy (current state)
2. **Phase 2**: Update clients to use new endpoints gradually
3. **Phase 3**: Implement backward compatibility layer if needed
4. **Phase 4**: Deprecate and remove legacy service

## 🧪 Demo Data

The service includes comprehensive mock data for MCP onboarding demonstrations:

- **Sample messages** with various statuses and channels
- **Realistic delivery analytics** with success/failure rates
- **Multi-channel delivery simulation** with different success rates per channel
- **Template examples** for common use cases (order updates, promotions, alerts)

All mock data is clearly marked with `mockData: true` in responses.

## 🔧 Configuration

Key configuration properties in `application.properties`:

```properties
# Service configuration
server.port=8084
spring.application.name=retailx-messaging-hub

# Modern messaging features
retailx.messaging.rate-limit.enabled=true
retailx.messaging.rate-limit.requests-per-minute=100
retailx.messaging.templates.enabled=true
retailx.messaging.analytics.enabled=true
retailx.messaging.webhooks.enabled=true

# Legacy compatibility
retailx.messaging.legacy-compatibility.enabled=true
retailx.messaging.legacy-compatibility.endpoint-prefix=/v1/legacy
```

## 🏥 Health Monitoring

- **Health endpoint**: `/actuator/health`
- **Metrics endpoint**: `/actuator/metrics`
- **Info endpoint**: `/actuator/info`

## 🔗 Integration with Other Services

This service integrates with:
- **RetailX Web Portal**: Displays order notifications and updates
- **RetailX Dashboard**: Shows messaging analytics and delivery metrics
- **RetailX Orders API**: Receives order event notifications
- **RetailX Inventory API**: Receives inventory alerts and updates

## 📝 Development Notes

### Mock Data Implementation
- All delivery operations are simulated for demo purposes
- Channel success rates are configurable and realistic
- Message storage is in-memory (replace with database in production)
- Template processing is mocked (implement real template engine in production)

### Production Readiness Checklist
- [ ] Replace H2 with production database (PostgreSQL/MySQL)
- [ ] Implement real message queue (RabbitMQ/Apache Kafka)
- [ ] Add authentication and authorization
- [ ] Implement real template engine
- [ ] Add comprehensive monitoring and alerting
- [ ] Configure external message providers (SendGrid, Twilio, etc.)
- [ ] Implement proper rate limiting with Redis
- [ ] Add message persistence and audit logging

## 🐛 Troubleshooting

### Common Issues

1. **Port conflicts**: Ensure port 8084 is available
2. **Java version**: Requires Java 17 or higher
3. **Memory issues**: Increase JVM heap size if needed: `-Xmx512m`

### Logs
```bash
# View application logs
docker logs retailx-messaging-hub

# Follow logs in real-time
docker logs -f retailx-messaging-hub
```

## 📞 Support

For issues related to this service:
- Check the health endpoint: `/actuator/health`
- Review application logs for detailed error information
- Consult the OpenAPI documentation at `/swagger-ui.html`
- Verify configuration in `application.properties`
