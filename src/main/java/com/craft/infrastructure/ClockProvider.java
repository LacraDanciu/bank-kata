package com.craft.infrastructure;

import java.time.LocalDate;

/**
 * @author flo
 * @since 02/07/15.
 */
public class ClockProvider {
    public LocalDate now() {
        return LocalDate.now();
    }
}
