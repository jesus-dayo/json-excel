package com.dayosoft.excel.writer;

import com.dayosoft.excel.model.RenderRequest;

public interface Writer<R> {

    R write(RenderRequest renderRequest);

}
