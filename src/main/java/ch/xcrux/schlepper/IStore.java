package ch.xcrux.schlepper;

/**
 * Buran.
 *
 * @author: ${USER} Date: 26.04.13 Time: 21:23
 */
public interface IStore {
    void addChange(DataId dataId, IChange change);

    void addChange(IChange change);

    <T extends IFacade> T getFacade(DataId dataId, Class<T> type);

    /**
     * Blocks until data is available.
     *
     * @param request
     * @param <T>
     * @return
     */
    <T extends Object> T requestAndGet(IGetRequest<T> request);
}
