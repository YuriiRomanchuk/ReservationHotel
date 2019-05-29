package com.reservation.controller;

import com.reservation.view.View;
import com.reservation.view.ViewModel;

public class WelcomeController {

    public View showIndexPage() {
        return new ViewModel("WEB-INF/jsp/index.jsp");
    }

}
