package ch.xcrux.schlepper;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 20:48
 */
public interface IChange<TData extends Object, TMetadata extends IMetadata,
        TRetData extends Object> {
    IRollbackableChange<TData, TMetadata, TRetData> createChange();
}
