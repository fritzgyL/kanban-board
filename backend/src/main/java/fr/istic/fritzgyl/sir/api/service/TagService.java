package fr.istic.fritzgyl.sir.api.service;

import fr.istic.fritzgyl.sir.api.domain.Tag;
import fr.istic.fritzgyl.sir.api.exception.DataNotFoundException;
import fr.istic.fritzgyl.sir.api.repository.CardRepository;
import fr.istic.fritzgyl.sir.api.repository.TagRepository;

public class TagService {

	private TagRepository tagRepository = new TagRepository();
	private CardRepository cardRepository = new CardRepository();

	public Iterable<Tag> getAllTags(long cardId) {
		return tagRepository.getTagsByCardId(cardId);
	}

	public Tag getTag(long tagId) {
		Tag tag = tagRepository.read(tagId);
		if (tag == null) {
			throw new DataNotFoundException("Tag with id " + tagId + " not found");
		}
		return tag;
	}

	public Tag addTag(long cardId, Tag tag) {
		return cardRepository.saveCardTag(cardId, tag);
	}

	public void removeTag(long tagId) {
		tagRepository.deleteAndRemoveFromCard(tagId);
	}

	public Tag updateTag(Tag tag) {
		return tagRepository.update(tag);
	}

}
