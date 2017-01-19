package xyz.upperlevel.graphicengine.api.window.event.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Action {

    @Getter public final int id;

    public boolean equals(Object object) {
        return object instanceof Action && ((Action) object).id == id;
    }
}