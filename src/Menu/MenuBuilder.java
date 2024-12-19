package Menu;

import Struct.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuBuilder {
    private final List<MenuItem> menu;
    private final String question;
    private final boolean onHelpMode;
    private final int helpKey;
    private final int exitKey;

    MenuBuilder(Builder builder) {
        this.menu = builder.menu;
        this.question = builder.question;
        this.onHelpMode = builder.onHelpMode;
        this.helpKey = builder.helpKey;
        this.exitKey = builder.exitKey;
    }

    public String getQuestion() {
        return question;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public boolean isOnHelpMode() {
        return onHelpMode;
    }

    public int getHelpKey() {
        return helpKey;
    }

    public int getQuitKey() {
        return exitKey;
    }

    public static class Builder {
        private List<MenuItem> menu  = new ArrayList<MenuItem>();
        private String question = "Where do you want to go ?";
        private boolean onHelpMode = false;
        private int helpKey = 0;
        private int exitKey;

        public Builder menu(List<MenuItem> menu) {
            this.menu = menu;
            this.exitKey = menu.size() + 1;
            return this;
        }

        public Builder question(String title) {
            this.question = question;
            return this;
        }

        public Builder onHelpMode(boolean onHelpMode) {
            this.onHelpMode = onHelpMode;
            return this;
        }

        public Builder helpKey(int helpKey) {
            this.helpKey = helpKey;
            return this;
        }

        public Builder exitKey(int exitKey) {
            this.exitKey = exitKey;
            return this;
        }

        public MenuBuilder build() {
            return new MenuBuilder(this);
        }
    }
}
