package melonslise.subwild.common.world.gen.feature;

import java.util.BitSet;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mojang.serialization.Codec;

import melonslise.subwild.common.config.SubWildConfig;
import melonslise.subwild.common.init.SubWildCapabilities;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class LiquidCavePlacement extends Placement<NoPlacementConfig>
{
	public LiquidCavePlacement(Codec<NoPlacementConfig> codec)
	{
		super(codec);
	}

	public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, NoPlacementConfig cfg, BlockPos pos)
	{
		World world = helper.field_242889_a.getWorld();
		if(!SubWildConfig.isAllowed(world) || !world.getCapability(SubWildCapabilities.NOISE_CAPABILITY).isPresent())
			return Stream.empty();
		ChunkPos chunkpos = new ChunkPos(pos);
		BitSet bitset = helper.func_242892_a(chunkpos, GenerationStage.Carving.LIQUID);
		return IntStream.range(0, bitset.length()).filter((index) -> bitset.get(index)).mapToObj((bit) -> new BlockPos(chunkpos.getXStart() + (bit & 15), bit >> 8, chunkpos.getZStart() + (bit >> 4 & 15)));
	}
}
