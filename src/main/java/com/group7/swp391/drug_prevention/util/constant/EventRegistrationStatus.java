package com.group7.swp391.drug_prevention.util.constant;

public enum EventRegistrationStatus {
    PENDING,          // Chờ phê duyệt
    APPROVED,         // Được phê duyệt
    REJECTED,         // Bị từ chối

    NOT_CHECKED_IN,   // Chưa điểm danh
    CHECKED_IN,       // Đã điểm danh
    CHECKED_OUT       // Đã rời sự kiện (tuỳ chọn)
}
