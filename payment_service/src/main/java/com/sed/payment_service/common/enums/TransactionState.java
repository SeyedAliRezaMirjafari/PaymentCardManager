package com.sed.payment_service.common.enums;

public enum TransactionState {
    BEGIN(1),
    COMMIT(2),
    ROLLBACK(3),
    FAILED(4);

    private final int code;
    TransactionState(int i) {
        this.code=i;
    }

    public int getCode() {
        return code;
    }
}
