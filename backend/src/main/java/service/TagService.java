package service;

import entity.Tag;
import repository.TagRepository;

public class TagService implements Service<Tag> {

	private TagRepository tagRepository = new TagRepository();

	@Override
	public Iterable<Tag> getAll() {
		return tagRepository.findAll();
	}

	@Override
	public Tag getById(long id) {
		return tagRepository.read(id);
	}

	@Override
	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public void delete(long id) {
		tagRepository.delete(getById(id));
	}

	@Override
	public Tag update(Tag t) {
		// TODO Auto-generated method stub
		return null;
	}
}
