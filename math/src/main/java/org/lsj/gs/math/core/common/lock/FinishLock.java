package org.lsj.gs.math.core.common.lock;

import java.util.concurrent.locks.ReentrantLock;

// 完成鎖
public class FinishLock {
    private final ReentrantLock lock; // 鎖
    private boolean isFinish; // 結束標誌

    public FinishLock(ReentrantLock lock) {
        this.lock = lock;
        this.isFinish = false;
    }

    // 加鎖
    public void lock(){
        this.lock.lock();
    }

    // 解鎖
    public void unlock(){
        this.lock.unlock();
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }
}
