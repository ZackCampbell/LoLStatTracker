package API;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class EndpointThrottler extends ThreadPoolExecutor {
    private final static int CORE_POOL_SIZE = 3;
    private final static int MAXIMUM_POOL_SIZE = 5;
    private final static int KEEP_ALIVE_SECONDS = 30;

    static EndpointThrottler instance;

    public EndpointThrottler(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);

        this.setRejectedExecutionHandler((r, executor) -> {
            try {
                Thread.sleep(1000);
                System.out.println("Sleeping for 1 second..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.execute(r);
        });
    }

    public static EndpointThrottler getInstance() {
        if (instance == null) {
            instance = new EndpointThrottler(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        }

        return instance;
    }

    /// For the extremely common case where we need to block on a single request
    /// response coming back to us
    public <T> void queue(EndpointRequest<T> endpointRequest) throws InterruptedException {
        this.queueMultiple(Collections.singletonList(endpointRequest));
    }

    /// Will block until all requests return
    public <T> void queueMultiple(List<EndpointRequest<T>> requests) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(requests.size());

        for (EndpointRequest<T> req : requests) {
            req.setLatch(latch);
            this.submit(req);
        }

        latch.await();
    }
}
