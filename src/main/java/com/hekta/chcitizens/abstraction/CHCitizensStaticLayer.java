package com.hekta.chcitizens.abstraction;

import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.PureUtilities.ClassLoading.ClassDiscovery;

import com.hekta.chcitizens.annotations.CHCitizensConvert;

/**
 *
 * @author Hekta, based on layton's StaticLayer
 */
public final class CHCitizensStaticLayer {

	private static final CHCitizensConvertor _convertor = InitConvertor();

	private CHCitizensStaticLayer() {
	}

	private static CHCitizensConvertor InitConvertor() {
		for(Class c : ClassDiscovery.getDefaultInstance().loadClassesWithAnnotation(CHCitizensConvert.class)) {
			if (CHCitizensConvertor.class.isAssignableFrom(c)) {
				CHCitizensConvert convert = (CHCitizensConvert) c.getAnnotation(CHCitizensConvert.class);
				if (convert.type() == Implementation.GetServerType()) {
					try {
						if (_convertor == null) {
							return (CHCitizensConvertor) c.newInstance();
						} else {
							System.err.println("[CommandHelper] [CHCitizens] More than one CHCitizensConvertor for this server type was detected!");
						}
					} catch (IllegalAccessException | InstantiationException exception) {
						System.err.println("[CommandHelper] [CHCitizens] Tried to instantiate the CHCitizensConvertor, but couldn't: " + exception.getMessage());
					}
				}
			} else {
				System.err.println("[CommandHelper] [CHCitizens] The CHCitizensConvertor " + c.getSimpleName() + " doesn't implement CHCitizensConvertor!");
			}
		}
		return _convertor;
	}

	public static MCCitizensPlugin getCitizens() {
		return _convertor.getCitizens();
	}

	public static MCCitizensTrait getCorrectTrait(MCCitizensTrait trait) {
		return _convertor.getCorrectTrait(trait);
	}
}