package xyz.upperlevel.graphicengine.launcher.gui;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.upperlevel.graphicengine.launcher.api.GELControlPanel;
import xyz.upperlevel.graphicengine.launcher.api.Game;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class GELGUIGameManager {

    @Getter public final TabPane pane;
    private final Map<Tab, Game> games = new HashMap<>();

    public void setup(Game game) {
        Tab tab = new Tab();
        tab.setText(game.getIdentity().getId());
        {
            WebView view = new WebView();
            view.getEngine().load(game.getClassLoader().getResource("presentation/presentation.html").toExternalForm());
            tab.setContent(view);
        }
        pane.getTabs().add(tab);
        games.put(tab, game);
    }

    public void setup() {
        GELControlPanel.g().getGames().forEach(this::setup);
    }

    public void load(File file) throws Exception {
        GELControlPanel.g().load(file);
        setup();
    }

    public Optional<Game> getGame(Tab tab) {
        return Optional.ofNullable(games.get(tab));
    }

    public Collection<Game> getGames() {
        return games.values();
    }
}