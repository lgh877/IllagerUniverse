/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside net.sweegy.illageruniverse as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package net.sweegy.illageruniverse;

import java.util.List;

public class FadingOutAnimation {
	private List<FadingOutAnimation> ownerList;
	public static final int DEFAULT_FADE_OUT = 5;
	public final int finalTime, maxFadeOutTime, animIdx;
	public int remainingFadeOutTime;
	public boolean shouldBeRemoved = false;

	public FadingOutAnimation(List<FadingOutAnimation> ownerList, int finalTime, int fadeOutTime, int animIdx) {
		this.ownerList = ownerList;
		this.finalTime = finalTime;
		this.animIdx = animIdx;
		remainingFadeOutTime = maxFadeOutTime = fadeOutTime;
	}

	public FadingOutAnimation(List<FadingOutAnimation> ownerList, int finalTime, int animIdx) {
		this(ownerList, finalTime, DEFAULT_FADE_OUT, animIdx);
	}

	public void tick() {
		remainingFadeOutTime--;
		shouldBeRemoved = remainingFadeOutTime == 0;
	}

	public void cleanup() {
		this.ownerList.remove(this);
	}
}