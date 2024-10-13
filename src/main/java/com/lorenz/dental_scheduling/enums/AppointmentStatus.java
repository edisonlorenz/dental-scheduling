package com.lorenz.dental_scheduling.enums;

import org.hibernate.event.internal.DefaultPersistOnFlushEventListener;

public enum AppointmentStatus {
    PENDING,
    SCHEDULED,
    CONFIRMED,
    RESCHEDULED,
    CANCELLED,
    COMPLETED
}
