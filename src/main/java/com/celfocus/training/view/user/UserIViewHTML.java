package com.celfocus.training.view.user;

import com.celfocus.training.domain.User;
import com.celfocus.training.util.DateUtils;
import com.celfocus.training.view.IView;

import java.util.Objects;

public class UserIViewHTML implements IView<User> {

    private static final String USER_TEMPLATE = "<div><h1>User</h1><span>%s</span><span>%s</span><span>%s</span></div>";

    /**
     * Renderiza o User no formato HTML
     *
     * @return HTML com as informarções do user
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

