package com.cnc.provider.api.oplog;

public interface OpLogService {

    void record(String opName, String systemCode, String moduleCode);
}
