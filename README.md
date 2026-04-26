Overview

This project is a backend system designed to detect and prevent phishing attacks originating from digital interactions such as URLs, QR codes, and NFC-based links.

The system follows a modular monolith architecture and performs real-time risk evaluation before allowing users to access external destinations.

Problem Statement

Users frequently interact with unknown links through QR codes, NFC tags, SMS, and web sources. These links can redirect users to malicious phishing pages that attempt to steal credentials or sensitive information.

Most existing systems detect threats after the user has already accessed the link. This project focuses on pre-access evaluation to prevent users from reaching harmful destinations.

Key Features
Real-time phishing detection
Multi-signal risk evaluation (URL structure, redirects, keywords)
Behaviour-based trust scoring
Secure redirect gateway for prevention
Modular architecture for scalability
Extendable design for AI and analytics
Architecture

The system uses a modular monolith design where different components are logically separated but run within a single application.

Flow:

Client → Interaction Module → Risk Evaluation → Trust Evaluation → Decision → Redirect Gateway

Modules
Interaction Module: Handles incoming scan requests and stores interaction data
Risk Module: Evaluates phishing signals and calculates risk score
Trust Module: Applies behavioural logic to compute trust score
Explainability Module: Generates user-friendly explanations (optional)
Analytics Module: Tracks usage patterns and trends
Gateway Module: Enforces redirect, warning, or block decisions
Core Workflow
User submits a URL (via scan or API)
Interaction is stored in the database
Risk module evaluates phishing indicators
Trust module calculates trust score
System classifies the link
Gateway controls user redirection
Technology Stack

Backend:

Java 17
Spring Boot
Spring Data JPA

Database:

PostgreSQL

Planned Enhancements:

Redis (caching)
AI-based explanation service
External threat intelligence integration
