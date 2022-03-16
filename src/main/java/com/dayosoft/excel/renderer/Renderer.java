package com.dayosoft.excel.renderer;

import com.dayosoft.excel.model.RenderRequest;

public interface Renderer<T extends RenderRequest> {

    void render(T renderRequest);

}
