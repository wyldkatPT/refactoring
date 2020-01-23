package com.celfocus.training.view.user;

import com.celfocus.training.domain.User;
import com.celfocus.training.util.DateUtils;
import com.celfocus.training.view.IView;

import java.util.Objects;

public class UserIViewXML implements IView<User> {

    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>";
    private static final String USER_TEMPLATE = XML_HEADER + "<name>%s</name><bd>%s</bd><older>%s</older>";

    /**
     * Renderiza o User no formato XML
     * @param user user a ser renderizado
     * @return XML com as informarções do user
     */
    @Override
    public String render(User user) {

        Objects.requireNonNull(user);


        return String.format(
                USER_TEMPLATE,
                user.getUsername(),
                DateUtils.toString(user.getBirthDate(),DateUtils.getSimpleDateFormat()),
                user.hasLegalAge());
    }

}
