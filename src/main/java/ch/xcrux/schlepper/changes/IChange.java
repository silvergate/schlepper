package ch.xcrux.schlepper.changes;

import ch.xcrux.schlepper.meta.IMetadata;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 20:48
 */
public interface IChange<TData extends Object, TMetadata extends IMetadata,
        TRetData extends Object> {
    IRollbackableChange<TData, TMetadata, TRetData> createChange();
}
