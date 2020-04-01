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
package com.gmail.filoghost.pvpgames.tasks;

import java.sql.SQLException;
import java.util.List;

import lombok.AllArgsConstructor;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.gmail.filoghost.pvpgames.Messages;
import com.gmail.filoghost.pvpgames.mysql.SQLManager;
import com.gmail.filoghost.pvpgames.mysql.SQLStat;
import com.gmail.filoghost.pvpgames.player.Mode;

@AllArgsConstructor
public class SendRankingTask implements Runnable {

	private CommandSender sender;
	private Mode mode;
	private String stat, translatedName;
	
	@Override
	public void run() {
		try {
			List<SQLStat> data = SQLManager.getTop(mode, stat, 10);
			
			sender.sendMessage(ChatColor.GOLD + "- Classifica " + translatedName + " (modalità: " + mode.getName() + ") -");
			int index = 1;
			for (SQLStat single : data) {
				sender.sendMessage(ChatColor.GRAY.toString() + index + ". " + ChatColor.WHITE + single.getName() + ChatColor.GRAY + " - " + single.getValue());
				index++;
			}
			sender.sendMessage("");
			
		} catch (SQLException e) {
			e.printStackTrace();
			sender.sendMessage(Messages.DATABASE_ERROR);
		}
	}

}
