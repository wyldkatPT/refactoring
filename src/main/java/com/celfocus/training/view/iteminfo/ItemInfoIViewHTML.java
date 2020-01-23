package com.celfocus.training.view.iteminfo;

import com.celfocus.training.domain.ItemInfo;
import com.celfocus.training.view.IView;

import java.util.Objects;

public class ItemInfoIViewHTML implements IView<ItemInfo> {

    private static final String ITEM_INFO_TEMPLATE = "<div><h1>ItemInfo</h1><span>%s</span><span>%s</span><span>%s</span></div>";


    /**
     * Renderiza o Item Info no formato HTML
     *
     * @param itemInfo Item info a ser renderizado
     * @return HTML com as informarções do Item Info
     */
    @Override
    public String render(ItemInfo itemInfo) {

        Objects.requireNonNull(itemInfo);

        return String.format(
                ITEM_INFO_TEMPLATE,
                itemInfo.getName(),
                itemInfo.getValue());
    }
}

