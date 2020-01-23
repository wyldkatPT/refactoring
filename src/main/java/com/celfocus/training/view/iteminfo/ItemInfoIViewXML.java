package com.celfocus.training.view.iteminfo;

import com.celfocus.training.domain.ItemInfo;
import com.celfocus.training.view.IView;

import java.util.Locale;
import java.util.Objects;

public class ItemInfoIViewXML implements IView<ItemInfo> {

    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>";
    private static final String ITEM_INFO_TEMPLATE = "<name>%s</name><value>%s</value>";

    /**
     * Renderiza o item Info no formato XML
     *
     * @param itemInfo Item Info a ser renderizado
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
