package com.celfocus.training.view;

import com.celfocus.training.domain.User;

public interface IView<T> {

    String render(T t);
}
