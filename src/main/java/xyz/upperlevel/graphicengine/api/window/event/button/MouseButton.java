package xyz.upperlevel.graphicengine.api.window.event.button;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MouseButton {

    @Getter public final int id;

    public boolean equals(Object object) {
        return object instanceof MouseButton ? ((MouseButton) object).id == id : super.equals(object);
    }
}
