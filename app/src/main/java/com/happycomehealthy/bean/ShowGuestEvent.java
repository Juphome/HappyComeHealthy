package com.happycomehealthy.bean;

import com.happycomehealthy.net.pojo.Guest;

/**
 * Created by shixinshan on 2018/5/25.
 */

public class ShowGuestEvent {
    private Guest guest;


    public ShowGuestEvent(Guest guest) {
        this.guest = guest;
    }
}
