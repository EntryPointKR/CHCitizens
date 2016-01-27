package com.hekta.chcitizens.core;

import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.exceptions.CRE.CREInvalidPluginException;
import com.laytonsmith.core.exceptions.CRE.CRENotFoundException;

import com.hekta.chcitizens.CHCitizens;
import com.hekta.chcitizens.abstraction.MCCitizensNPC;
import com.hekta.chcitizens.abstraction.MCCitizensNPCRegistry;
import com.hekta.chcitizens.abstraction.MCCitizensPlugin;

/**
 *
 * @author Hekta
 */
public final class CHCitizensStatic {

	public static MCCitizensPlugin getCitizensPlugin(Target t) {
		MCCitizensPlugin plugin = CHCitizens.getCitizensPlugin();
		if (plugin != null) {
			return plugin;
		} else {
			throw new CREInvalidPluginException("Needed plugin Citizens not found.", t);
		}
	}

	public static MCCitizensNPCRegistry getNPCRegistry(Target t) {
		MCCitizensNPCRegistry registry = CHCitizens.getNPCRegistry();
		if (registry != null) {
			return registry;
		} else {
			throw new CREInvalidPluginException("Needed plugin Citizens not found.", t);
		}
	}

	public static MCCitizensNPC getNPC(int id, Target t) {
		MCCitizensNPC npc = getNPCRegistry(t).getNPC(id);
		if (npc != null) {
			return npc;
		} else {
			throw new CRENotFoundException("There is no existing NPC with this id (" + id + ").", t);
		}
	}
}