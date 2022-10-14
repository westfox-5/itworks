package it.itworks.models;

import it.itworks.annotations.Input;

public class Fragment {
    @Input(position = 1, max = 10_000)
    private Integer fragment;

    public Fragment() {
    }

    public Integer getFragment() {
        return fragment;
    }

    public void setFragment(Integer fragment) {
        this.fragment = fragment;
    }

    public Fragment clone() {
        Fragment f = new Fragment();
        f.fragment = fragment;
        return f;
    }
}
