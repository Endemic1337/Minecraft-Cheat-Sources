package net.minecraft.util;

public class MovementInput
{
    /**
     * The speed at which the player is strafing. Postive numbers to the left and negative to the right.
     */
    public static float moveStrafe;

    /**
     * The speed at which the player is moving forward. Negative numbers will move backwards.
     */
    public static float moveForward;
    public boolean jump;
    public boolean sneak;

    public boolean isJumping() {
		return jump;
	}


	public boolean isSneaking() {
		return sneak;
	}



	public void updatePlayerMoveState()
    {
    }


    public float getForward() {
        return moveForward;
    }


    public float getStrafe() {
        return moveStrafe;
    }


	 public void setForward(float moveForward) {
        this.moveForward = moveForward;
    }
}
