package com.example.capstone3.Service;


import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.HealthAssessment;
import com.example.capstone3.Repository.HealthAssessmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class HealthReportService {
    private final HealthAssessmentRepository healthAssessmentRepository;

    // Generate HTML Health Report
    public String generateHealthReport(Integer assessmentId) {

        // Get Health Assessment
        HealthAssessment assessment =
                healthAssessmentRepository.findById(assessmentId).orElse(null);

        if (assessment == null) {
            throw new ApiException("Health assessment not found");
        }

        // Get User
        String userName = assessment.getUser().getFullName();
        String userEmail = assessment.getUser().getEmail();

        // Format Assessment Date
        String assessmentDate = "";

        if (assessment.getAssessmentDate() != null) {
            assessmentDate = assessment.getAssessmentDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }

        // Create HTML
        String html = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">

                    <meta name="viewport"
                          content="width=device-width, initial-scale=1.0">

                    <title>Health Assessment Report</title>

                    <style>

                        body {
                            margin: 0;
                            padding: 0;
                            background-color: #f4f7fb;
                            font-family: Arial, Helvetica, sans-serif;
                            color: #1f2937;
                        }

                        .container {
                            width: 90%;
                            max-width: 900px;
                            margin: 40px auto;
                            background-color: white;
                            border-radius: 16px;
                            overflow: hidden;
                            box-shadow: 0 8px 30px rgba(0,0,0,0.08);
                        }

                        .header {
                            background: linear-gradient(
                                135deg,
                                #0f766e,
                                #14b8a6
                            );
                            color: white;
                            padding: 35px;
                        }

                        .header h1 {
                            margin: 0;
                            font-size: 30px;
                        }

                        .header p {
                            margin-top: 10px;
                            opacity: 0.9;
                        }

                        .section {
                            padding: 25px 35px;
                            border-bottom: 1px solid #e5e7eb;
                        }

                        .section h2 {
                            margin-top: 0;
                            color: #0f766e;
                            font-size: 20px;
                        }

                        .info-grid {
                            display: grid;
                            grid-template-columns: 1fr 1fr;
                            gap: 15px;
                        }

                        .info-box {
                            background-color: #f8fafc;
                            padding: 15px;
                            border-radius: 10px;
                        }

                        .label {
                            font-size: 12px;
                            color: #6b7280;
                            margin-bottom: 5px;
                        }

                        .value {
                            font-size: 16px;
                            font-weight: bold;
                        }

                        .ai-box {
                            background-color: #ecfdf5;
                            border-left: 5px solid #0f766e;
                            padding: 20px;
                            border-radius: 10px;
                            line-height: 1.7;
                        }

                        .footer {
                            padding: 25px 35px;
                            text-align: center;
                            color: #6b7280;
                            font-size: 12px;
                        }

                        .warning {
                            background-color: #fff7ed;
                            border-left: 5px solid #f97316;
                            padding: 15px;
                            border-radius: 8px;
                            margin-top: 15px;
                        }

                        @media (max-width: 600px) {

                            .info-grid {
                                grid-template-columns: 1fr;
                            }

                            .container {
                                width: 95%;
                                margin: 15px auto;
                            }

                            .header,
                            .section,
                            .footer {
                                padding: 20px;
                            }
                        }

                    </style>
                </head>

                <body>

                    <div class="container">

                        <!-- Header -->
                        <div class="header">

                            <h1>Health Assessment Report</h1>

                            <p>
                                Personalized Health Assessment
                            </p>

                        </div>


                        <!-- Patient Information -->
                        <div class="section">

                            <h2>Patient Information</h2>

                            <div class="info-grid">

                                <div class="info-box">

                                    <div class="label">
                                        Patient Name
                                    </div>

                                    <div class="value">
                                        %s
                                    </div>

                                </div>


                                <div class="info-box">

                                    <div class="label">
                                        Email
                                    </div>

                                    <div class="value">
                                        %s
                                    </div>

                                </div>


                                <div class="info-box">

                                    <div class="label">
                                        Assessment Date
                                    </div>

                                    <div class="value">
                                        %s
                                    </div>

                                </div>


                                <div class="info-box">

                                    <div class="label">
                                        Health Trend
                                    </div>

                                    <div class="value">
                                        %s
                                    </div>

                                </div>

                            </div>

                        </div>


                        <!-- Health Assessment -->
                        <div class="section">

                            <h2>Health Assessment</h2>

                            <div class="info-grid">

                                <div class="info-box">

                                    <div class="label">
                                        Profile Snapshot
                                    </div>

                                    <div class="value">
                                        %s
                                    </div>

                                </div>


                                <div class="info-box">

                                    <div class="label">
                                        Extracted Values
                                    </div>

                                    <div class="value">
                                        %s
                                    </div>

                                </div>

                            </div>

                        </div>


                        <!-- AI Conclusion -->
                        <div class="section">

                            <h2>AI Health Summary</h2>

                            <div class="ai-box">

                                %s

                            </div>

                            <div class="warning">

                                This report is generated for informational
                                purposes and does not replace professional
                                medical advice.

                            </div>

                        </div>


                        <!-- Follow Up -->
                        <div class="section">

                            <h2>Follow-up</h2>

                            <div class="info-grid">

                                <div class="info-box">

                                    <div class="label">
                                        Next Assessment
                                    </div>

                                    <div class="value">
                                        %s
                                    </div>

                                </div>


                                <div class="info-box">

                                    <div class="label">
                                        Current Assessment
                                    </div>

                                    <div class="value">
                                        %s
                                    </div>

                                </div>

                            </div>

                        </div>


                        <!-- Footer -->
                        <div class="footer">

                            Healthcare Platform<br>

                            This report was generated automatically.

                        </div>

                    </div>

                </body>
                </html>
                """.formatted(
                userName,
                userEmail,
                assessmentDate,
                assessment.getTrend(),
                assessment.getProfileSnapshot(),
                assessment.getExtractedValues(),
                assessment.getAiConclusion(),
                assessment.getNextDueDate(),
                assessment.getIsCurrent()
        );

        return html;
    }
}

