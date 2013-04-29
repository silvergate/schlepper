package ch.xcrux.schlepper.interceptors;

import java.util.List;

/**
 * Buran.
 *
 * @author: ${USER} Date: 30.04.13 Time: 00:30
 */
public interface IThreadSwitcher {
    void invoke(List<Interception> interceptions);
}
