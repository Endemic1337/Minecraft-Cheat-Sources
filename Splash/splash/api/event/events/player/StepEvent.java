package splash.api.event.events.player;

import me.hippo.systems.lwjeb.event.Cancelable;
import net.minecraft.entity.Entity;

public class StepEvent extends Cancelable {

    private Entity entity;
    private float stepHeight;

    public StepEvent(Entity entity, float stepHeight) {
        this.entity = entity;
        this.stepHeight = stepHeight;
    }

    public void setStepHeight(float stepHeight) {
        this.stepHeight = stepHeight;
    }

    public Entity getEntity() {
        return entity;
    }

    public float getStepHeight() {
        return stepHeight;
    }

}

