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
package com.gmail.filoghost.pvpgames.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import wild.api.command.CommandFramework;

import com.gmail.filoghost.pvpgames.PvPGames;
import com.gmail.filoghost.pvpgames.player.PvPGamer;
import com.gmail.filoghost.pvpgames.player.Status;

public class GoCommand extends CommandFramework {
	
	public GoCommand() {
		super(PvPGames.getInstance(), "go");
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		
		PvPGamer player = PvPGames.getPvPGamer(CommandValidate.getPlayerSender(sender));
		CommandValidate.isTrue(player.getStatus() == Status.SPECTATOR, "Puoi usare questo comando solamente da spettatore.");
		CommandValidate.minLength(args, 1, "Utilizzo comando: /go <giocatore>");
		
		Player targetPlayer = Bukkit.getPlayerExact(args[0]);
		CommandValidate.notNull(targetPlayer, "Quel giocatore non è online o non sta giocando.");
		
		PvPGamer target = PvPGames.getPvPGamer(targetPlayer);
		CommandValidate.isTrue(target.getStatus() == Status.GAMER, "Quel giocatore non è online o non sta giocando.");
		
		player.getPlayer().teleport(targetPlayer.getLocation());
		player.sendMessage(ChatColor.GRAY + "Ti sei teletrasportato da " + target.getName());
		
	}

}
