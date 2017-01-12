package xyz.upperlevel.graphicengine.api.window.event.key;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.upperlevel.graphicengine.api.window.event.action.Action;

@RequiredArgsConstructor
public class Key {

    @Getter public final int id;

    public boolean equals(Object object) {
        return object instanceof Key ? ((Key) object).id == id : super.equals(object);
    }
}
