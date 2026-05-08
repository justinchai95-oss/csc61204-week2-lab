package com.example.meditrackapp;

public class PatientReportDemo {
    public static void main(String[] args) {
        PatientReportFormatter formatter = new PatientReportFormatter();
        ReportFileSaver saver  = new ReportFileSaver();
        AuditLogger logger     = new AuditLogger();

        String report = formatter.format("Maria Santos", 80);
        saver.save(report, "report_001.txt");
        Notifier notifier = new EmailNotifier();
        notifier.send(report, "doctor@meditrack.com");
        notifier = new SmsNotifier();
        notifier.send(report, "doctor@meditrack.com");
        logger.log("Report generated for Maria Santos");
    }
}

