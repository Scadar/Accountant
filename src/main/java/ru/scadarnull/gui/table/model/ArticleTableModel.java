package ru.scadarnull.gui.table.model;

import ru.scadarnull.model.Article;
import ru.scadarnull.saveload.SaveData;

public class ArticleTableModel extends MainTableModel {

    private static final int TITLE = 0;

    public ArticleTableModel(String[] columns) {
        super(SaveData.getInstance().getArticleList(), columns);
    }


    @Override
    protected void updateData() {
        data = SaveData.getInstance().getArticleList();
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (data.isEmpty()) return null;
        Article article = (Article) data.get(row);
        return switch (column) {
            case TITLE -> article.getTitle();
            default -> null;
        };
    }

}
