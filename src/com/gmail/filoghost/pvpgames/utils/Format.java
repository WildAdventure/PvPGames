/*
 * Copyright (c) 2020, Wild Adventure
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 * 4. Redistribution of this software in source or binary forms shall be free
 *    of all charges or fees to the recipient of this software.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.gmail.filoghost.pvpgames.utils;

import java.util.Set;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import wild.api.translation.Translation;

import com.google.common.collect.Sets;

public class Format {

	private static final Set<PotionEffectType> USELESS_AMPLIFIERS = Sets.newHashSet(
			PotionEffectType.BLINDNESS,
			PotionEffectType.CONFUSION,
			PotionEffectType.FIRE_RESISTANCE,
			PotionEffectType.INVISIBILITY,
			PotionEffectType.NIGHT_VISION,
			PotionEffectType.WITHER
	);
	
	public static String getRoman(int i) {
		switch (i) {
			case 1:		return "I";
			case 2:		return "II";
			case 3:		return "III";
			case 4:		return "IV";
			case 5:		return "V";
			case 6:		return "VI";
			case 7:		return "VII";
			case 8:		return "VIII";
			case 9:		return "IX";
			case 10:	return "X";
			default: 	return Integer.toString(i);
		}
	}
	
	
	public static String formatEffectName(PotionEffect potion) {
		String name = Translation.of(potion.getType());
		
		if (!USELESS_AMPLIFIERS.contains(potion.getType())) {
			name += " " + getRoman(potion.getAmplifier() + 1);
		}
		
		return name;
	}
	
	public static String formatEffectDuration(PotionEffect potion) {
		if (potion.getDuration() < Integer.MAX_VALUE) {
			
			int seconds = potion.getDuration() / 20;
			int minutes = 0;
			
			if (seconds >= 60) {
				minutes = seconds / 60;
				seconds = seconds % 60;
			}
			
			return "(" + minutes + ":" + (seconds < 10 ? "0" : "") + seconds + ")";

		} else {
			return "(Permanente)";
		}
	}
	
	
}
